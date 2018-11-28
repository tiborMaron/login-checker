$(document).ready(function(){

    $('.toggler').on('click', null, function(){

        // Toggle result page
        $(this).parent().parent().find('.results').slideToggle();

        // Get credentials
        var email = $("#email").val();
        var password = $("#password").val();

        // AJAX calls
        var platforms = $(".platform-container");
        $.each(platforms, function () {
            $(this).find('.fa-circle').removeClass("far fa-circle").addClass("fas fa-spinner loading");


        });

    });

});