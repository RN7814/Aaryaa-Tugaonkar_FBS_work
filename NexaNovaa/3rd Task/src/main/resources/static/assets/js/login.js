import { request, session } from "./api.js";
const form = document.querySelector("#login-form");
const button = document.querySelector("#login-button");
const error = document.querySelector("#login-error");
button.disabled = true;
try {
  const current = await session();
  if (current.user) { location.replace("/"); }
  button.disabled = false;
} catch (problem) { error.textContent = problem.message; }
form.addEventListener("submit", async (event) => {
  event.preventDefault();
  error.textContent = "";
  button.disabled = true;
  button.textContent = "Signing in…";
  try {
    await request("/auth/login", "POST", Object.fromEntries(new FormData(form)));
    location.replace("/");
  } catch (problem) {
    error.textContent = problem.message;
    button.disabled = false;
    button.textContent = "Sign in →";
  }
});
