
var token = localStorage.getItem('token');
$(document).ready(function() {
    var urlParams = new URLSearchParams(window.location.search);
    var vacancyId = urlParams.get('vacancyId'); // Получите идентификатор вакансии из URL (например, через параметр запроса)
    console.log('sssssssssss');
    // Выполните запрос к серверу для получения информации о вакансии по идентификатору
    $.ajax({
        url: '/api/vacancies/' + vacancyId,
        type: 'GET',
        headers: {
            Authorization: 'Bearer ' + token
        },
        success: function(vacancy) {
            displayVacancy(vacancy);
        },
        error: function() {
            console.log('Ошибка при выполнении запроса');
        }
    });

    function displayVacancy(vacancy) {
        // Отображаем информацию о вакансии на странице
        $('.vacancy-section h1').text(vacancy.jobTitle);
        $('.vacancy-section .vacancy-image img').attr('src', vacancy.img);
        $('.vacancy-section .vacancy-details .vacancy-location').text(vacancy.location);
        $('.vacancy-section .vacancy-details .vacancy-salary').text(vacancy.salary);
        $('.vacancy-section .vacancy-details .vacancy-description').text(vacancy.description);
    }



    // Обработчики кнопок
    $('#edit-vacancy').click(function() {

    });

    $('#delete-vacancy').click(function() {
        $.ajax({
            url: '/api/vacancies/' + vacancyId,
            method: 'DELETE',
            success: function (response) {
                console.log('vacancy successfully deleted');
                window.location.href = 'my_account.html';
                // Выполните дополнительные действия при успешном удалении
            },
            error: function (xhr, status, error) {
                console.log('an error occurred while deleting a vacancy', error);
            }
        });
    });






});




