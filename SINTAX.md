```
process main
	let fun teste msg1 msg2 = msg1+msg2 in
		receive(idProcess1, 60) // This will wait until process1 response or 60 seconds timeout
		receive(idProcess2, 60) // This will wait until process2 response or 60 seconds timeout
end

process idProcess1
	let fun ping = wait(3) // This will lock the processe by 3 seconds
                   send("main", "ping") // This will send message 'ping' to the mainProcess
        in ping()
end

process idProcess2
	let fun pong = wait(5) // This will lock the processe by 5 seconds 
                   send("main", " pong") // This will send message 'pong' to the mainProcess
    in pong() 
end
```
