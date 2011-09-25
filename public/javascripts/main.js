$(function() {
    $('header').mouseenter(function(e){
        $('.content-inner').fadeOut(800);
        $('#content').append('<div class="grid_12 faded">YO, NIGGA!</div>');
        e.stopPropagation();
        e.preventDefault();
    });
    $('header').mouseleave(function() {
        $('.content-inner').fadeIn('fast', function() { 
            $('.faded').remove();
        })
    });
});
