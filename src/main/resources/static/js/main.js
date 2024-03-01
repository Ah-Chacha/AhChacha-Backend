const toggleBtn = document.querySelector('.navbar_toggleBtn');
const menu = document.querySelector('.navbar_menu')
const user = document.querySelector('.navbar_user')

toggleBtn.addEventListener('click', () => {
    menu.classList.toggle('active');
    user.classList.toggle('active');
})