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
        var numOfSuccess = 0;
        $.each(platforms, function () {
            var platform = $(this);
            platform.find('.fa-circle').removeClass("far fa-circle").addClass("fas fa-spinner loading");

            var path = "/api/" + this.id;
            $.ajax(path, {
                async: false,
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(credentials),

                success: function (result) {
                    if (result === true) {
                        platform.find('.fa-spinner').removeClass("fas fa-spinner loading").addClass("fas fa-check-square");
                        platform.find('.iconstatusmessage').text("SUCCESS").css("color", "green");
                        numOfSuccess++;
                    } else {
                        platform.find('.fa-spinner').removeClass("fas fa-spinner loading").addClass("fas fa-window-close");
                        platform.find('.iconstatusmessage').text("NOT FOUND").css("color", "red");
                    }
                },

                error: function () {
                    platform.find('.fa-spinner').removeClass("fas fa-spinner loading").addClass("fas fa-circle");
                    platform.find('.iconstatusmessage').text("ERROR");
                }
            })

        });

        // Show summary page
        $(this).parent().parent().find('.summary').slideToggle();
        $("#summary").text("Found " + numOfSuccess + " matches!");
    });

});