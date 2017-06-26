package net

import com.sitexa.gankot.net.SweetApi
import org.junit.Test

/**
 * Created by open on 16/06/2017.
 */

class SweetApiTest {

    @Test
    fun testTopSweet() {
        val api = SweetApi.create()

        api.topSweet(10, 1).subscribe({ result ->
            result.forEach { sweet ->
                println("sweet=$sweet")
            }
        }, { error ->
            println(error.message)
        }, {
            println("completed")
        })
    }

    @Test
    fun testLatestSweet() {
        val api = SweetApi.create()

        api.latestSweet(10, 1).subscribe({ result ->
            result.forEach { sweet ->
                println("sweet=$sweet")
            }
        }, { error ->
            println(error.message)
        }, {
            println("completed")
        })
    }

    @Test
    fun testMediaList() {
        val api = SweetApi.create()
        api.getMediasBySweet(1).subscribe({ result ->
            result.forEach { media ->
                println("media=$media")
            }
        }, { error ->
            println(error.localizedMessage)
        })
    }
}