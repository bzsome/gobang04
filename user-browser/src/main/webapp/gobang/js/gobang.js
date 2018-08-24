var me = true;
var chessBoard = [];

for(var i = 0; i < 19; i++) {
	chessBoard[i] = [];
	for(var j = 0; j < 19; j++) {
		chessBoard[i][j] = 0;
	}
}

var chess = document.getElementById("chess");
var context = chess.getContext("2d");
context.strokeStyle = "#BFBFBF";

var logo = new Image();
logo.src = "img/bg.png";
logo.onload = function() {
	context.globalAlpha = 0.8;
	context.drawImage(logo, 0, 0, 456, 456);
	drawChessBoard();

}
var drawChessBoard = function() {
	for(var i = 0; i < 19; i++) {
		//纵向画线
		context.moveTo(12 + i * 24, 12);
		context.lineTo(12 + i * 24, 444);
		context.stroke();

		//横向画线
		context.moveTo(12, 12 + i * 24);
		context.lineTo(444, 12 + i * 24);
		context.stroke();
	}
}

var oneStep = function(i, j, me) {
	context.beginPath();
	context.arc(12 + i * 24, 12 + j * 24, 10, 0, 2 * Math.PI);
	context.closePath();
	var gradient = context.createRadialGradient(12 + i * 24 + 2, 12 + j * 24 - 2, 10, 12 + i * 24 + 2, 12 + j * 24 - 2, 0);
	if(me) {
		gradient.addColorStop(0, "#0A0A0A");
		gradient.addColorStop(1, "#636766");
	} else {
		gradient.addColorStop(0, "#D3D3D3");
		gradient.addColorStop(1, "#F9F9F9");
	}

	context.fillStyle = gradient;
	context.globalAlpha = 1;
	context.fill();
}

chess.onclick = function(e) {
	var x = e.offsetX;
	var y = e.offsetY;
	var i = Math.floor(x / 24);
	var j = Math.floor(y / 24);
	if(chessBoard[i][j] == 0) {
		if(me) {
			chessBoard[i][j] = 1;
		} else {
			chessBoard[i][j] = 2;
		}
		oneStep(i, j, me);
		me = !me;
	}

}