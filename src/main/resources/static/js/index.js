$(document).ready(function(){

    $('.toggler').on('click', null, function(){
        $(this).parent().parent().find('.results').slideToggle();

    });

});