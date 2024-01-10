Antoine Serry

TP Java - Veiller à Run "MainInterface" pas "Main"

Mes ajouts au code du TP :

    - Gestion des déplacements liés au ticker de rendu et pas aux interruptions dont la fréquence est déterminée par l'OS
        Permet un fonctionnement plus réactif et propre, réduit les variations d'un utilisateur à l'autre
        La fonction move if possible ne prend plus qu'une distance absolue, la direction du déplacement est déterminée par l'orientation du personnage définie au préalable
    