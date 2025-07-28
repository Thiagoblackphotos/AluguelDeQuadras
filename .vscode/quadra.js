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

// Mudar slide a cada 3 segundos
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
    
    // PEGA OS DADOS DO FORMULARIO
    const nome = document.querySelector('input[name="nome"]').value;
    const telefone = document.querySelector('input[name="telefone"]').value;
    const horario = document.querySelector('input[name="horario"]').value;

    // CRIA O OBJETO COM OS DADOS
    const reserva = {
        nome: nome,
        telefone: telefone,
        horario: horario
    };

    //ENVIA OS DADOS PARA BACKEND
    fetch('http://localhost:8000/reservar', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(reserva) //transforma o objeto em JSON para o java poder ler
    })
    .then(response=> {
        if (response.ok) {
            alert('Reserva enviada com sucesso!');
            document.querySelector('.booking-form').reset();

        } else {
            alert('Erro ao enviar reserva. Tente novamente.');
        }
    })

    .catch(error => {
        alert('Erro de conexão com o servidor.');
        console.error(error);
    }) 
    
    
});