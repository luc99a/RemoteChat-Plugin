<?php 

	include("RemoteChatAPI.class.php");
	
	$remote_chat = new RemoteChatAPI("127.0.0.1", 2112, "RemoteChat", "RemoteChat");
	$remote_chat->send_chat_message("Hello World");

?>