```
process mainProcess
    let func teste 
    ... let var msg1 = receive(idProcess1, 60) // This will wait until process1 response or 60 seconds timeout
    ... let var msg2 = receive(idProcess2, 60) // This will wait until process2 response or 60 seconds timeout

    let var msg = msg1 + msg2 // This will print 'ping pon'
end

process idProcess1
    let func ping
    ...
    wait(3) // This will lock the processe by 3 seconds
    send(mainProcess, "ping") // This will send message 'ping' to the mainProcess
end

process idProcess2
    let func pong
    ...
    wait(5) // This will lock the processe by 5 seconds
    send(mainProcess, "pong") // This will send message 'pong' to the mainProcess
end
```