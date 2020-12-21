const card = post => {
    return `
                    <div class="card">
                        <img class="card-img-top" src="files?id=${post.file}" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${post.name}</h5>
                            <p class="card-text">${post.description}</p>
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
        return fetch('/profile/posts', {method: 'get'}).then(response => response.json())
    }
}

class User {
    static fetch() {
        return fetch('/userInfo', {method: 'get'}).then(response => response.json())
    }
}

$(document).ready(function () {
    Post.fetch().then(backendPosts => {
        posts = backendPosts
        renderPosts(posts)
    })
    User.fetch().then(user => {
        renderUser(user);
    });
})

function renderUser(user){
    const $profile = document.querySelector('#row-names');
    let firstName = user.firstName;
    let lastName = user.lastName;
    let names = firstName.concat(' ').concat(lastName);
    $profile.innerHTML = `
                <h5 class="card-title">${names}</h5>
                `
}

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

function isLiked(id, callback) {
    $.ajax({
        url: '/isLiked',
        type: "post",
        dataType: 'JSON',
        data: {id: id},
        success: function (response) {
            return callback(response);
        }
    });
}