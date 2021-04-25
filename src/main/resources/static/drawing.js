function drawPlayer(player, scaleX = 1, scaleY = 1) {
    const x = player.position.x * scaleX;
    const y = player.position.y * scaleY;
    console.log('player', player.position.x, player.position.y, x, y);
    const speed = player.speed.magnitude * 20;
    const angle = player.angle;

    context.save();
    context.translate(x,y);
    context.rotate(angle);

    context.beginPath();
    context.rect(5 * scaleX , -2 * scaleY , 4 * scaleX , 4 * scaleY );
    context.rect(-5 * scaleX , -5 * scaleY , 10 * scaleX , 10 * scaleY );
    context.fill();



    context.beginPath();
    context.strokeStyle = 'red';
    context.moveTo(0,0)
    context.lineTo(speed * scaleX, 0);
    context.stroke();


    context.restore();
}

function reponseReceived(data) {
    context.beginPath();
    context.clearRect(0, 0, canvas.clientWidth, canvas.clientHeight);
    const scaleX = canvas.clientWidth/data.width;
    const scaleY = canvas.clientHeight/data.height;
    console.log('canvas', canvas.clientWidth, canvas.clientHeight, data.width, data.height,scaleX, scaleY);
    data.players.forEach(player => drawPlayer(player, scaleX, scaleY));
}

function sizeCanvas(width = 600, height = 600) {
    canvas.width = width;
    canvas.height = height;

    // flip vertically
    context.translate(canvas.width / 2, canvas.height / 2);
    context.scale(1, -1);
    context.translate(-canvas.width / 2, -canvas.height / 2);
    context.save();
}