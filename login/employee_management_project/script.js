const translations = {
    en: {
      "FERMA BOD": "BOD FARM",
      "S.C L&D LUCA IND": "S.C L&D LUCA IND",
      "RO 6652160": "RO 6652160",
      "Utilizator:": "User:",
      "Înregistrează o problemă:": "Report an issue:",
      "Submit": "Trimite"
    },
    ro: {
      "BOD FARM": "FERMA BOD",
      "S.C L&D LUCA IND": "S.C L&D LUCA IND",
      "RO 6652160": "RO 6652160",
      "User:": "Utilizator:",
      "Report an issue:": "Înregistrează o problemă:",
      "Trimite": "Submit"
    }
  };

  let currentLang = 'ro';

  function translatePage() {
    const elements = document.querySelectorAll('body *');
    elements.forEach(element => {
      if (element.childNodes.length === 1 && element.childNodes[0].nodeType === Node.TEXT_NODE) {
        const text = element.textContent.trim();
        const translatedText = translations[currentLang][text];
        if (translatedText) {
          element.textContent = translatedText;
        }
      }
    });

    // Toggle the language
    currentLang = currentLang === 'ro' ? 'en' : 'ro';

    // Update the button text
    document.querySelectorAll('.lang-ro, .lang-en').forEach(el => el.style.display = 'none');
    document.querySelector(`.lang-${currentLang === 'ro' ? 'en' : 'ro'}`).style.display = 'inline';
  }

  document.getElementById('langToggle').addEventListener('click', translatePage);

  // Initial translation
  translatePage();