import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.sample.homework.domain.Operation
import org.sample.homework.domain.Status
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

    @Test
    fun shouldSerializeEnum() {
        val result = serializer.serialize(Operation._3DAUTH)
        assertEquals(result, "\"3DAUTH\"")
    }

    @Test
    fun shouldSerializeEnumInObject() {
        val result = serializer.serialize(DummyWithEnum(Operation._3DAUTH, Status.WAITING))
        println(result)
    }

    @Test
    fun shouldDeserializeObjectWithEnum() {
        val json = """{"x": "3D", "y": "APPROVED"}"""
        val result = serializer.deserialize(json, DummyWithEnum::class.java)

        assertEquals(result.x, Operation._3D)
        assertEquals(result.y, Status.APPROVED)
    }

    private data class Dummy(val a: String, val b: Int)
    private data class DummyWithEnum(val x: Operation, val y: Status)
}