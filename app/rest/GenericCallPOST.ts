import axios, {AxiosResponse} from "axios";

abstract class GenericCallPOST {

    protected call(
        url: string,
        headers: { [header: string]: string; },
        body: any,
        success: (data: any) => void
    ): void {

        axios.post(
            url,
            body,
            {
                headers: headers
            }
        ).then((response: AxiosResponse): void => {
            console.dir(response);
            success(response.data);
        });
    }
}

export default GenericCallPOST;
