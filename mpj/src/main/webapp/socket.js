console.log("Happy Audience :-)")

const socket = new WebSocket("ws://localhost:9080/mpj/notifications")

socket.onopen = e => console.log('Opening a socket', e)
window.setInterval(_ => {
        if (socket.readyState === socket.OPEN) {
            socket.send('Hello from js ' + new Date())
        }
    }, 8000)

socket.onmessage = e => console.log('Message back from server:', e.data)

window.setTimeout(_ => socket.close(), 30000)

socket.onclose = e => console.log('Close socket')