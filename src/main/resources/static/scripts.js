function toggleCategory(index) {
    var collapseId = 'collapse-category-' + index;
    var collapseElement = document.getElementById(collapseId);
    
    var collapses = document.querySelectorAll('.collapse');
    collapses.forEach(function(collapse) {
        if (collapse.id !== collapseId && collapse.classList.contains('show')) {
            var bsCollapse = new bootstrap.Collapse(collapse);
            bsCollapse.hide();
        }
    });

    var bsCollapse = new bootstrap.Collapse(collapseElement);
    bsCollapse.toggle();
}
