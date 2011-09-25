$(function() {
    $('header').mouseenter(function(e){
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

           // jquery is odd here, but the animation looks cool so I'll leave it.
           var counter = count * 75;
            message.queue("yay", function(next) {
                clone.stop().queue("inner", function(inner) {
                    $(this).delay(counter, "yay")
                    .animate({ top:'10px', left: '10px' }, counter);
                    
                    $(this).delay(counter+1, "yay")
                    .css({ 
                        top: '10px',
                        left: '10px',
                        position: 'relative', 
                        float: 'left', 
                        display:'block'})
                    .addClass('clone done');

                    inner();
                });
                clone.dequeue("inner");
                next();
            });
            count++;
        });
        $('#content').append(message);
        message.dequeue("yay"); 
        $('.content-inner').stop().fadeOut(400);       
        // Now, queue animations up to a slide-togggle type of animation.
    });
    $('header').mouseleave(function() {
        $('.content-inner').fadeIn('fast', function() { 
           $('.subliminal').remove();
           $('.content-inner').show();
        })
    });
});
