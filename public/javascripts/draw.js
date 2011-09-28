$(function() {
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
});
