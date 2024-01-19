document.addEventListener('DOMContentLoaded', function() {
    var registerLink = document.getElementById('register-link');

    registerLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по открытию страницы регистрации, например:
        window.location.href = 'registration.html';
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var homeLink = document.getElementById('home-link');

    homeLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'index.html';
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var loginLink = document.getElementById('login-link');

    loginLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'login.html';
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var aboutLink = document.getElementById('about-link');

    aboutLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'about_us.html';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var employersLink = document.getElementById('employers');

    employersLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'employers.html';
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var jobLink = document.getElementById('job_search');

    jobLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'job_search.html';
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var accountLink = document.getElementById('my_account');

    accountLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'my_account.html';
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var jobLink = document.getElementById('job_search_2');

    jobLink.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'job_search.html';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var goMain = document.getElementById('add_vacancy');

    goMain.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке

        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'index.html';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var exit = document.getElementById('exit');

    exit.addEventListener('click', function(event) {
        event.preventDefault(); // Отменяем стандартное действие перехода по ссылке
        localStorage.clear();
        // Здесь можно выполнить действия по переходу на главную страницу, например:
        window.location.href = 'index.html';
    });
});