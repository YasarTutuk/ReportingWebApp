import org.junit.Assert.*
import org.junit.Test
import org.sample.homework.service.ApiClient
import org.sample.homework.service.GsonSerializer

class ClientTest {
    private val jsonString: String = """{"a":"test", "b": 100}"""
    private val dummyObj: Dummy = Dummy("test", 100)

    @Test
    fun shouldGet() {
        val client = ApiClient("http://httpbin.org/get", GsonSerializer())

        val value = client.get()
        println(value)
        assertTrue(value.isNotEmpty())
    }

    @Test
    fun shouldPostWithObject() {
        val client = ApiClient("http://httpbin.org/post", GsonSerializer())

        val (value, _) = client.post(dummyObj, Any::class.java)
        assertTrue(value != null)

        println(value)
    }

    @Test
    fun shouldPostWithString() {
        val client = ApiClient("http://httpbin.org/post", GsonSerializer())

        val (value, _) = client.post(jsonString, Any::class.java)
        assertTrue(value != null)

        println(value)
    }
}