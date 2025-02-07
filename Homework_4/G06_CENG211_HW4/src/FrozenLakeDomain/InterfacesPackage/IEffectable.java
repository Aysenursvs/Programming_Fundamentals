package FrozenLakeDomain.InterfacesPackage;

import FrozenLakeDomain.ResearcherPackage.Researcher;

public interface IEffectable {
    /**
     * Applies an effect to the given researcher based on the specified square.
     * If returns 0, that means researcher will not move this square.
     * If returns 1, that means researcher will move to the next square.
     *
     * @param researcher the researcher to which the effect will be applied
     * @param square the square that determines the effect on the researcher
     * @return an integer representing the result of the effect
     */
    public int effect(Researcher researcher, ISquare square);
}
