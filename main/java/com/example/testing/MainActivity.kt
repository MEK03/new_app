package com.example.testing

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shopify.buy3.GraphCallResult
import com.shopify.buy3.GraphCallResultCallback
import com.shopify.buy3.GraphClient
import com.shopify.buy3.GraphError
import com.shopify.buy3.GraphResponse
import com.shopify.buy3.QueryGraphCall
import com.shopify.buy3.Storefront


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        executeGraphQuery()
    }

    fun goToCategoryPage() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    private fun executeGraphQuery() {
        val client: GraphClient = GraphClient.build(
            this,
            "xennio.myshopify.com",
            "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"
        )

        val query = Storefront.query { rootQuery: Storefront.QueryRootQuery ->
            rootQuery.shop { shopQuery: Storefront.ShopQuery ->
                shopQuery
                    .name()
                    .products(first = 3) { productConnection ->
                        productConnection.edges { productEdge ->
                            productEdge.node { product ->
                                product.title()
                            }
                        }
                    }
            }
        }


        class MyGraphCallback : GraphCallResultCallback<Storefront.QueryRoot> {
            fun onResponse(response: GraphResponse<Storefront.QueryRoot>) {
                val name = response.data?.shop?.name
                val products = response.data?.shop?.products?.edges

            }


            fun onFailure(error: GraphError) {
                Log.e("InUse", "Failed to execute query", error)
            }

            override operator fun invoke(result: GraphCallResult<Storefront.QueryRoot>) {
                when (result) {
                    is GraphCallResult.Success -> onResponse(result.response)
                    is GraphCallResult.Failure -> onFailure(result.error)
                }
            }
        }

        val call: QueryGraphCall = client.queryGraph(query)
        val callback = MyGraphCallback()
        call.enqueue(callback)


    }
}