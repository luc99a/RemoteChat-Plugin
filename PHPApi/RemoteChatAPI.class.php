<?php
	
		class RemoteChatAPI {
			
			private $ip;
			private $port;
			private $username;
			private $password;
	
			public function __construct($ip, $port, $username, $password) {
				$this->ip = $ip;
				$this->port = $port;
				$this->username = $username;
				$this->password = $password;
			}
			
			public function send_chat_message($message) {
				$socket = fsockopen($this->ip, $this->port);
				if (!is_resource($socket)) {
					throw new Exception("Failed to connect");
				}
				fputs($socket, $this->username . ":" . $this->password . "@" . $message);
				fclose($socket);
			}
			
		}
		
?>