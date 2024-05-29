// ********************* REVIEWS TABS *********************//
const faq_1 = document.querySelector(".faq-1")
const faq_2 = document.querySelector(".faq-2")
const faq_3 = document.querySelector(".faq-3")
const faq_4 = document.querySelector(".faq-4")

const link_1 = document.querySelector(".open-1");
const link_2 = document.querySelector(".open-2");
const link_3 = document.querySelector(".open-3");
const link_4 = document.querySelector(".open-4");

faq_1.addEventListener("click", () => {
  faq_1.classList.toggle("active")
  link_1.classList.toggle("active")
})


faq_2.addEventListener("click", () => {
  faq_2.classList.toggle("active")
  link_2.classList.toggle("active")
})


faq_3.addEventListener("click", () => {
  faq_3.classList.toggle("active")
  link_3.classList.toggle("active")
})


faq_4.addEventListener("click", () => {
  faq_4.classList.toggle("active")
  link_4.classList.toggle("active")
})