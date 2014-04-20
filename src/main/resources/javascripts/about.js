function init_about(){
    function brushClick(evt){
        evt = evt || window.event;
        var target = evt.target || evt.srcElement;
        if (target.className === "brush")
        {
            window['jimschubert.us brush'] = new Brushes[target.text]({
                context: (document.getElementById('canvas').getContext('2d')),
                size: 12,
                pressure: 1
            });
        }
    }
    // delegate clicks to the ul
    var selector = document.querySelector('ul#brushSelector')
    selector.addEventListener('click',brushClick,false);
}
// this file will almost always load after jquery when deferred, use vanilla js
window.addEventListener("DOMContentLoaded", init_about, true);