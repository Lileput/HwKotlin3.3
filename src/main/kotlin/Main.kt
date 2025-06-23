package netology

fun main() {

}

data class Message(
    val id: Int,
    val senderId: Int,
    val text: String,
    var isRead: Boolean = false,
)

data class Chat(
    val id: Int,
    val userId : Int,
    val messages : MutableList<Message> = mutableListOf()
)

class MessageService {
    private val chats = mutableListOf<Chat>()

    fun getUnreadChatsCount() : Int = chats.asSequence()
        .filter { chat -> chat.messages.any { !it.isRead } }
        .count()

    fun getChats() : List<Chat> = chats.toList()

    fun getLastMessages() : List<String> = chats.asSequence()
        .map { chat -> chat.messages.lastOrNull()?.text ?: "Нет сообщений!" }
        .toList()

    fun createMessage(userId: Int, text: String) {
        val chat = chats.find { it.userId == userId } ?: Chat(chats.size + 1, userId).also { chats.add(it) }
        chat.messages.add(Message(chat.messages.size + 1, userId, text))
    }

    fun deleteMessage(userId: Int, messageId: Int) {
        chats.find { it.userId == userId }?.messages?.removeIf { it.id == messageId }
    }

    fun deleteChat(userId: Int) {
        chats.removeIf { it.userId == userId }
    }

    fun getMessages(userId: Int, count: Int): List<Message> {
        val chat = chats.find { it.userId == userId } ?: return emptyList()

        val messagesToReturn = chat.messages.takeLast(count)
        messagesToReturn.forEach { it.isRead = true }

        return messagesToReturn
    }

    fun reset() {
        chats.clear()
    }
}