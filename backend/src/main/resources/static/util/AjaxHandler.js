function get(path, handleResponse, hanldeError) {
    fetch(path, {headers: {"Content-Type": "application/json; charset=utf-8"}})
        .then(response => response.json())
        .then(responseJson => handleResponse(responseJson))
        .catch(error => hanldeError(error));
}

export {
    get
}
