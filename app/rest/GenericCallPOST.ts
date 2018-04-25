import axios from "axios";

abstract class GenericCallPOST {

    protected call(
        url: string,
        headers: { [header: string]: string; },
        body: any,
        success: (data: any) => any
    ): void {

        console.dir(url);
        console.dir(headers);
        console.dir(body);

        axios.post(
            url,
            body,
            {
                headers: headers
            }
        ).then((response) => {
            success(response.data);
        });
    }
}

export default GenericCallPOST;
