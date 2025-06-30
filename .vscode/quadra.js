document.addEventListener('DOMContentLoaded', function() {
    const slides = document.querySelectorAll('.quadra-slide');
    const prevBtn = document.querySelector('.prev-btn');
    const nextBtn = document.querySelector('.next-btn');
    let currentSlide = 0;
    
    // Mostrar slide inicial
    showSlide(currentSlide);
    
    // Event listeners para os botões
    prevBtn.addEventListener('click', prevSlide);
    nextBtn.addEventListener('click', nextSlide);
    
    // Navegação por teclado
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            prevSlide();
        } else if (e.key === 'ArrowRight') {
            nextSlide();
        }
    });
    
    // Função para mostrar slide específico
    function showSlide(n) {
        // Esconder todos os slides
        slides.forEach(slide => {
            slide.classList.remove('active');
        });
        
        // Ajustar índice se for além dos limites
        if (n >= slides.length) {
            currentSlide = 0;
        } else if (n < 0) {
            currentSlide = slides.length - 1;
        } else {
            currentSlide = n;
        }
        
        // Mostrar slide atual
        slides[currentSlide].classList.add('active');
    }
    
    // Próximo slide
    function nextSlide() {
        showSlide(currentSlide + 1);
    }
    
    // Slide anterior
    function prevSlide() {
        showSlide(currentSlide - 1);
    }
    
    // Auto-avanço (opcional)
    // setInterval(nextSlide, 5000);
});