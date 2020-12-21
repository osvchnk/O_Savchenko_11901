const card = post => {
    return `
                    <div class="card">
                        <img class="card-img-top" src="files?id=${post.file}" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${post.name}</h5>
                            <p class="card-text">${post.description}</p>
<!--                            <a href="#" class="btn btn-primary">Go somewhere</a>-->
                        </div>
                        <div class="like">
                            <i id="${post.id}" class="far fa-heart" onclick="like(id)"></i>
                        </div>
                    </div>
        `
}

let posts = []

class Post {
    static fetch() {
        console.log("started")
        return fetch('/home/posts', {method: 'get'}).then(response => response.json())
    }
}

$(document).ready(function () {
    Post.fetch().then(backendPosts => {
        posts = backendPosts
        renderPosts(posts)
    })
});


function renderPosts(_posts = []) {
    const $posts = document.querySelector('#posts');
    if (_posts.length > 0) {
        $posts.innerHTML = _posts.map(post => card(post)).join(' ')
    } else {
        $posts.innerHTML = `<div class="center">Постов пока нет</div>`
    }
}

function like(postId) {
    let btn = document.getElementById(postId);

    if (btn.classList.contains("far")) {
        btn.classList.remove("far");
        btn.classList.add("fas");
        $.ajax({
            url: "/like",
            type: "post",
            data: {postId: postId},
            success: function (response) {
            }
        })
    } else {
        btn.classList.remove("fas");
        btn.classList.add("far");
        $.ajax({
            url: "/dislike",
            type: "post",
            data: {postId: postId},
            success: function (response) {
            }
        })
    }
}