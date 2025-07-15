// Slider Automático
let currentSlide = 0;
const slides = document.querySelectorAll('.slide');

function showSlide(n) {
    slides.forEach(slide => slide.classList.remove('active'));
    currentSlide = (n + slides.length) % slides.length;
    slides[currentSlide].classList.add('active');
}

function nextSlide() {
    showSlide(currentSlide + 1);
}

// Mudar slide a cada 5 segundos
setInterval(nextSlide, 5000);

// Inicializar Mapa
const map = L.map('map').setView([-23.5505, -46.6333], 15);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Adicionar marcador
const marker = L.marker([51.19628992606257, 6.916284844059892]).addTo(map)
    .bindPopup('Golden Sports<br>')
    .openPopup();

// Abrir Google Maps ao clicar
map.on('click', function() {
    window.open('https://www.google.com/maps?q=51.19628992606257, 6.916284844059892');});

// Animação ao rolar
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

// Form submission
document.querySelector('.booking-form').addEventListener('submit', function(e) {
    e.preventDefault();
    alert('Reserva enviada com sucesso! Entraremos em contato para confirmação.');
    this.reset();
});