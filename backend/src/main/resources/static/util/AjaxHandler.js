function get(path, handleResponse, handleError) {
    fetch(path, {headers: {"Content-Type": "application/json; charset=utf-8"}, method: "GET"})
        .then(response => response.json())
        .then(responseJson => handleResponse(responseJson))
        .catch(error => handleError(error));
}

function post(path, body, handleResponse, handleError) {
    fetch(path, {headers: {"Content-Type": "application/json; charset=utf-8"}, method: "POST", body: JSON.stringify(body)})
        .then(response => response.json())
        .then(responseJson => handleResponse(responseJson))
        .catch(error => handleError(error))
}

export {
    get, post
}
