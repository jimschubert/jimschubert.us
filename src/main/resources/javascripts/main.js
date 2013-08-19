$(function() {
    $('.fake-noscript').remove();

    var canvasTest = document.createElement('canvas');
    if(canvasTest != null && typeof canvasTest.getContext === "function") {
        // drawing is allow, load draw.js

        $.getScript('/js/draw.js', function() {
            $('.draw-hint').show();
        });
    }
    
    $('#header').mouseenter(function(e){
        $('.subliminal').remove();
        $('.content-inner').stop().show().css({opacity:'1.0'});

        var message = $('<div class="subliminal"></div>');
        message.height( $('.content-inner').height() );
        message.width( $('.content-inner').width() );
       
       var count = 1;
       $('.hidden-message').each(function(a,b) {
            var clone = $(b).clone();
            var offset = $(b).position();
            message.append(clone);
            clone.css({ 
                position: 'absolute',
                top: offset.top,
                left: offset.left
            });
            clone.addClass('clone');

        });
        $('#content').append(message);
        $('.content-inner').stop().fadeOut(800, function() {
            var count = 1,
                offset = $('#content').offset();

            $('.clone').each(function(a,b) {
                var c = count,
                    clone = $(b),
                    counter = c * 75;

                clone.stop().animate({ 
                    top: offset.top, 
                    left: offset.left,
                    opacity: 0
                }, 700, 'easeInOutBack', function() { 
                    $(this).addClass('done');
                    $(this).css({
                        top:'',
                        left:'',
                        display:'inline',
                        position:'relative',
                        float:'left'
                    });
                    $(this).delay(counter).animate({ opacity: 1 });
                });
            });
        });       
    });
    $('#header').mouseleave(function() {
        $('.subliminal').remove();
        $('.content-inner').fadeIn('fast', function() { 
            $('.content-inner').show();
        });
    });
});
