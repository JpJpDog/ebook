const postFetch = (url, params, callBack) => {
    fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(params)
    }).then(rsp => {
        rsp.json().then(response => callBack(response));
    })
}

export {postFetch};