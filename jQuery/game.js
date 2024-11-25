$(document).ready(function () {
    // Get screen resolution (adjusted for browser space)
    var width = screen.width - 100;
    var height = screen.height - 200;

    // Function to generate a random alphabet between A - Z
    function getRandomLetter() {
        var randomCode = Math.floor(Math.random() * (90 - 65 + 1)) + 65;
        return String.fromCharCode(randomCode);
    }

    // Function to generate random color
    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    // Function to create a bubble with random letter and random color
    function createBubble() {
        var letter = getRandomLetter();
        var color = getRandomColor();
        var bubble = $('<div class="bubble"></div>');
        bubble.text(letter);
        bubble.css({
            'left': Math.random() * width + 'px',
            'top': Math.random() * height + 'px',
            'background-color': color
        });
        $('#gameContainer').append(bubble);

        // Remove the bubble after 5 seconds
        setTimeout(function () {
            bubble.remove();
        }, 5000);
    }

    // Start by creating a bubble every 2 seconds
    setInterval(createBubble, 2000);

    // Listen to keypress events and check if the correct letter is pressed
    $(document).keypress(function (event) {
        var pressedKey = String.fromCharCode(event.which).toUpperCase();

        // Check if there's a bubble with the matching letter
        $('.bubble').each(function () {
            if ($(this).text() === pressedKey) {
                $(this).fadeOut(500, function () {
                    $(this).remove(); // Remove bubble after fade out
                });
            }
        });
    });
});
