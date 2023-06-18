// script.js

document.getElementById('contact-form').addEventListener('submit', function(event) {
  event.preventDefault(); 
  alert("Muchas gracias por su interés, nos pondremos en contacto con usted en breve.");
});

var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');
var firma = false;

canvas.addEventListener('mousedown', activarFirma);
canvas.addEventListener('mousemove', dibujar);
canvas.addEventListener('mouseup', desactivarFirma);
canvas.addEventListener('mouseleave', desactivarFirma);

document.getElementById('limpiar').addEventListener('click', limpiarCanvas);

function activarFirma(event) {
    firma = true;
    context.beginPath();
    context.moveTo(event.clientX - canvas.getBoundingClientRect().left, event.clientY - canvas.getBoundingClientRect().top);
}

function desactivarFirma() {
    firma = false;
}

function dibujar(event) {
    if (!firma) return;
    context.lineWidth = 2;
    context.lineCap = 'round';
    context.strokeStyle = '#000';
    context.lineTo(event.clientX - canvas.getBoundingClientRect().left, event.clientY - canvas.getBoundingClientRect().top);
    context.stroke();
    context.beginPath();
    context.moveTo(event.clientX - canvas.getBoundingClientRect().left, event.clientY - canvas.getBoundingClientRect().top);
}

function limpiarCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height);
}

function play(){
  var audio = document.getElementById("miAudio");
  audio.volume = 0.05;
  audio.play();
}

function stop(){
  document.getElementById("miAudio").pause();

}

  
  