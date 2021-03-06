package js

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.scalajs.js.timers._

@JSExport
object OnlineMemberList {
  
  @JSExport
  def shoutMessageLogic(
        userName : String,
        send : html.Button,
        message : html.Input,
        connection : dom.WebSocket) {
    val onConnectionOpenedHandler = 
        DomUtil.setupShoutMessenger(userName, send, message, connection)
    WebSocketUtil.setup(
        connection,
        onConnectionOpenedHandler,
        new WebSocketMessageHandler(userName, connection)
            with ChallengeHandler
            with ChallengeAcceptHandler
            with ChallengeDeclinedHandler
            with UserUpdateHandler)
  }
}