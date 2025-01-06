function confirmSubmit(event) {
    const isConfirmed = confirm("제출 하시겠습니까?");

    if (isConfirmed) {
        alert("제출되었습니다.");
    } else {
        event.preventDefault();
    }
}
