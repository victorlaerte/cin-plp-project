# Examples

```
{
   wait(10)
}
```

```
{
   let var x=10 in wait(x)
}
```

```
{
   let fun sleep time = wait(time) in sleep(10)
}
```

```
{
  {
   process p1 
       let fun sum x = x in sum(1)
   end
  }
}

```
```
{
   {
	  process
	     p1 let fun sleep time = wait(time) in sleep(10)
	  end
   }
}
```

```
{
   let fun esperar time callback dois = wait(time, callback, dois) in esperar(10, "mensagem1", "mensagem2")
}
```

```
{
   {
 	process p1
		let fun sleep time = wait(time) in sleep(10)
 	end
 	process p2
 		let fun dormir x = wait(x) in dormir(5)
 	end
   }
}
```

```
{
   {
 	process p1
		let fun sleep time callback = wait(time, callback) in sleep(10, "pong")
 	end
 	process p2
 		let fun dormir tempo retorno = wait(tempo, retorno) in dormir(5, "ping")
 	end
   }
}
```

```
{
   {
 	process p1
		let fun enviarMensagem idProcesso mensagem = send(idProcesso, mensagem) in wait(10, enviarMensagem("p2", "Teste"))
 	end
   }
}
```

```
{
   {
        process p2
		let fun printRemoteMsg timeout = receive(timeout) in printRemoteMsg(120)
 	end
   	process p1
		let fun enviarMensagem idProcesso mensagem = send(idProcesso, mensagem) in wait(10, enviarMensagem("p2", "Teste"))
 	end
   }
}
```