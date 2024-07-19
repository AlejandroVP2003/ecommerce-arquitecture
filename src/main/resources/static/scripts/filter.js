document.getElementById('clearButton').addEventListener('click', function() {
    var form = document.getElementById('filterForm');
    document.getElementById('startDate').value = '';
    document.getElementById('endDate').value = '';
    form.submit();
});