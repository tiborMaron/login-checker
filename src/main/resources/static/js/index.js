$(document).ready(function () {

    $('.toggler').on('click', null, function () {

        // Toggle result page
        $(this).parent().parent().find('.results').slideToggle();

        // Get credentials
        var credentials = {};
        credentials["email"] = $("#email").val();
        credentials["password"] = $("#password").val();

        // AJAX calls
        var platforms = $(".platform-container");
        $.each(platforms, function () {
            var platform = $(this);
            platform.find('.fa-circle').removeClass("far fa-circle").addClass("fas fa-spinner loading");

            var path = "/api/" + this.id;
            $.ajax(path, {
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(credentials),

                success: function (resultData) {
                    platform.find('.fa-spinner').removeClass("fas fa-spinner loading").addClass("fas fa-check-square");
                },

                error: function () {
                    platform.find('.fa-spinner').removeClass("fas fa-spinner loading").addClass("fas fa-times");
                }
            })

        });

    });

});