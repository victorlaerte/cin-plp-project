```
{
   {
	process p2
	// This will wait until some message is received or 120 seconds timeout
	let fun printRemoteMsg timeout = receive(timeout) in printRemoteMsg(120) 
	end

	process p1
	/ This will send message 'Teste' to the processe p2
	let fun enviarMensagem idProcesso mensagem = send(idProcesso, mensagem)
		in wait(10, enviarMensagem("p2", "Teste"))
	end
   }
}
```
