function drawPlayer(player) {
    const x = player.position.x;
    const y = player.position.y;
    const speed = player.speed.magnitude * 20;
    const angle = player.angle;

    context.save();
    context.translate(x,y);
    context.rotate(angle);

    context.beginPath();
    context.rect(5, -2 , 4, 4);
    context.rect(-5, -5 , 10, 10);
    context.fill();



    context.beginPath();
    context.strokeStyle = 'red';
    context.moveTo(0,0)
    context.lineTo(speed, 0);
    context.stroke();


    context.restore();
}

function reponseReceived(data) {
    context.beginPath();
    context.clearRect(0, 0, canvas.clientWidth, canvas.clientHeight);
    data.players.forEach(player => drawPlayer(player));
}

function sizeCanvas() {
    canvas.width = 300;
    canvas.height = 300;

    // flip vertically
    context.translate(canvas.width / 2, canvas.height / 2);
    context.scale(1, -1);
    context.translate(-canvas.width / 2, -canvas.height / 2);
    context.save();
}