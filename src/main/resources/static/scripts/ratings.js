const form = document.querySelector("#rating-filter");
const container = document.querySelector("#rating-search-result");
const notFound = document.querySelector("#rating_not_found");


form.addEventListener('submit', (event) => {
    event.preventDefault();
    requestRatings();
});

function requestRatings() {
    var formData = new FormData(form);
    var data = Array
        .from(formData)
        .filter(function([k, v]) { return v });
    var params = new URLSearchParams(data);

    fetch('/ratings/get?' + params.toString(), {
            method: 'GET'
    })
    .then(response => response.json())
    .then(data => createTable(data))
    .catch(error => console.error(error));
}

function createTable(data) {
    container.innerHTML = "";

    if (!data.length) {
        notFound.style.display = "block";
        return;
    }
    notFound.style.display = "none";

    const table = document.createElement('table');

    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    const headers = ['Фамилия', 'Имя', 'Отчество', 'Дисциплина', ...data[0].ratings.map(rating => rating.part)];
    headers.forEach(header => {
        const th = document.createElement('th');
        th.textContent = header;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');
    for (let d of data) {
        const row = document.createElement('tr');
        const rowData = [d.lastName, d.firstName, d.patronymic, d.discipline, ...d.ratings.map(rating => rating.averageScore.toFixed(2))];
        rowData.forEach(cell => {
            const td = document.createElement('td');
            td.textContent = cell;
            row.appendChild(td);
        });
        tbody.appendChild(row);
    }

    table.appendChild(tbody);
    table.classList.add("table_sort");
    container.appendChild(table);
}