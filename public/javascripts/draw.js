$(function() {
    var canvas = $('#canvas');
    var context = canvas.get(0).getContext('2d');
    var brush = new Brushes.marker({
        context: context
    });

    $(window).mousedown(function(evt) {
        evt = evt || window.event;
        if (brush) {
            console.log(evt);
            brush.strokeStart(evt.clientX - evt.screenX, evt.clientY - evt.screenY);
        }
    });

    $(window).mouseup(function(evt) {
        canvas.onmousemove = null;
    });

    $(window).mousemove(function(evt) {
        evt = evt || window.event;
        var w = document.embeds["wacom-plugin"],
            pressure = w&&w.pressure?w.pressure:1.0,
            isEraser = w&&w.isEraser,
            brushSize = 1.0; // TODO
            if(brush) { 
                brush.BRUSH_SIZE = brushSize;
                brush.stroke(evt.clientX - evt.screenX, evt.clientY - evt.screenY);
            }
    });
});
