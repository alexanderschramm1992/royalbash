abstract class GenericCallGET {

    protected call(
        url: string,
        header: { [header: string]: string; },
        success: (data: string) => void
    ): void {

        let xhr = new XMLHttpRequest();

        xhr.open('GET', url);

        xhr.onreadystatechange = function() {
            if (xhr.readyState>3 && xhr.status==200) success(xhr.responseText);
        };

        for (let headerKey in header) {
            xhr.setRequestHeader(headerKey, header[headerKey]);
        }

        xhr.send();
    }

}

export default GenericCallGET;
