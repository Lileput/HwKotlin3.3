import netology.MessageService
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MessageServiceTest {
    private var service: MessageService = MessageService()

    @Before
    fun setup() {
        service = MessageService()
        service.reset()
    }

    @Test
    fun createMessage_createsChat() {
        service.createMessage(1, "Привет")
        assertEquals(1, service.getChats().size)
    }

    @Test
    fun createMessage_addsMessage() {
        service.createMessage(1, "Привет")
        service.createMessage(1, "Как дела?")
        assertEquals(2, service.getChats()[0].messages.size)
    }

    @Test
    fun deleteMessage_deletesMessage() {
        service.createMessage(1, "Привет")
        service.createMessage(1, "Как дела?")
        service.deleteMessage(1, 1)
        assertEquals(1, service.getChats()[0].messages.size)
    }

    @Test
    fun deleteChat_deletesChat() {
        service.createMessage(1, "Привет")
        service.deleteChat(1)
        assertEquals(0, service.getChats().size)
    }

    @Test
    fun getUnreadChatsCount_returnsCount() {
        service.createMessage(1, "Привет")
        service.createMessage(2, "Как дела?")
        assertEquals(2, service.getUnreadChatsCount())
    }

    @Test
    fun getLastMessages_returnsLastMessage() {
        service.createMessage(1, "Привет")
        service.createMessage(1, "Как дела?")
        val lastMessages = service.getLastMessages()
        assertEquals("Как дела?", lastMessages[0])
    }

    @Test
    fun getLastMessages_returnsNoMessagesIfEmpty() {
        val lastMessages = service.getLastMessages()
        assertEquals(0, lastMessages.size)
    }


    @Test
    fun reset_clearsChats() {
        service.createMessage(1, "Привет")
        service.reset()
        assertEquals(0, service.getChats().size)
    }

    @Test
    fun getMessages_returnsMessages() {
        service.createMessage(1, "Привет")
        val messages = service.getMessages(1, 1)
        assertEquals(1, messages.size)
    }

    @Test
    fun getMessages_marksAsRead() {
        service.createMessage(1, "Привет")
        val messages = service.getMessages(1, 1)
        assertTrue(messages[0].isRead)
    }
}