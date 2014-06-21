$(function() {
    $('.fake-noscript').remove();
    
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

    if(Modernizr.canvas) {
        var canvas = $('#canvas'),
            canvasElem = $(canvas).get(0);
        var context = canvas.get(0).getContext('2d');

        context.scale(0,0);

        context.canvas.width = $(document).width();
        context.canvas.height = $(document).height();

        var brush = new Brushes.boxes({
            context: context,
            randomize: true,
            size: 12,
            pressure: 1
        });

        canvasElem.onmousemove = function(e) {
            if(canvas.drawing) {
                brush.stroke(e.pageX, e.pageY);
            }
        };

        canvasElem.onmousedown = function(e) {
            canvas.drawing = true;
            brush = window['jimschubert.us brush'] || brush;
            brush.strokeStart(e.pageX, e.pageY);
        };

        canvasElem.onmouseup = function(e) {
            canvas.drawing = false;
            brush.strokeEnd();
        };

        $(window).bind("mousemove", function(e) {
            canvasElem.onmousemove.call(this, e.originalEvent);
        });

        $(window).bind("mouseup", function(e) {
            canvasElem.onmouseup.call(this, e.originalEvent);
        });

        $(window).bind("mousedown", function(e) {
            canvasElem.onmousedown.call(this, e.originalEvent);
        });

        $(window).bind("resize", function() {
            var canvas = $('#canvas').get(0);
            canvas.width = $(document).width();
            canvas.height = $(document).height();
        });
    }
});
