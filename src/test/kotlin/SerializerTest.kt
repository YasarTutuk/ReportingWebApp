import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.sample.homework.service.GsonSerializer
import org.sample.homework.service.Serializer

class SerializerTest {
    private lateinit var serializer: Serializer
    private val jsonString: String = """{"a":"test", "b": 100}"""
    private val dummyObj: Dummy = Dummy("test", 100)

    @Before
    fun setUp() {
        serializer = GsonSerializer()
    }

    @Test
    fun shouldSerializeObject() {
        val result = serializer.serialize(dummyObj)
        assertEquals(result.replace("\\s".toRegex(), ""), jsonString.replace("\\s".toRegex(), ""))
    }

    @Test
    fun shouldDeserializeToObject() {
        val result = serializer.deserialize(jsonString, Dummy::class.java)

        assertEquals(result.a, dummyObj.a)
        assertEquals(result.b, dummyObj.b)
    }

}