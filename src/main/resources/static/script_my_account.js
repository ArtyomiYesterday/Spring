$(document).ready(function() {
    // Скрытие формы резюме при загрузке страницы
    $('#resume-form').hide();

    // Обработчик клика на кнопку "Создать резюме"
    $('#create-resume-button').click(function() {
        // Скрытие кнопки "Создать резюме"
        $(this).hide();
        // Отображение формы резюме
        $('#resume-form').show();
    });

    // Обработчик отправки формы резюме
    $('#create-resume-form').submit(function(e) {
        e.preventDefault();
        // Дополнительный код для обработки отправки формы резюме
        // ...
    });
});